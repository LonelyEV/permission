package ones.quzhigang.permission.common;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * 分页查询公共工具类
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class PaginationQuery {

	/** 每页显示条数limit */
	private Integer pageSize=10;

	/** offset */
	private Integer pageNumber=0;

	/** 总数据条数 */
	private Integer total;
	/** 总页数 */
	private Integer totalPage;

	/** 排序字段：默认值是null */
	private String sort;

	/** 排序：默认值:asc */
	private String order;
	
	private Integer limit=0;
	
	private Integer offset=0;
	
	private Integer roleId;
	
    private List<Integer> roleIds;
    
	public List<Integer> getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(List<Integer> roleIds) {
		this.roleIds = roleIds;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	private Map<String,Object> paramsMap=Collections.emptyMap();
	//检索返回记录集-结果集
	private List<?> result = Collections.emptyList();
	
	public PaginationQuery() {
		super();
	}

	public Integer getPageSize() {
		if(getLimit().intValue()>0){
			setPageSize(getLimit());
		}		
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageNumber() {
		
		if(pageNumber==null||pageNumber==0){
			pageNumber= 1;
		}
		 if(getLimit().intValue()>0&&getOffset().intValue()>0){
		    	pageNumber=getOffset()/getLimit()+1;
		    }
		
	   
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.totalPage=total/this.pageSize>0?total/this.pageSize+1:total/this.pageSize;
		this.total = total;
	}

	public List<?> getResult() {
		return result;
	}

	public void setResult(List<?> result) {
		this.result = result;
	}

	public Map<String, Object> getParamsMap() {
		return paramsMap;
	}

	public void setParamsMap(Map<String, Object> paramsMap) {
		this.paramsMap = paramsMap;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
}
