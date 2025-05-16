package Model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class Agent {

    String id;

    String email;

    String name;

    List<IssueType> expertise;
}
