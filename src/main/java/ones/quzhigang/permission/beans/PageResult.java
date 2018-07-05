package ones.quzhigang.permission.beans;

import com.google.common.collect.Lists;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@Builder
public class PageResult<T> {

    private List<T> data = Lists.newArrayList();

    private int total = 0;
}
