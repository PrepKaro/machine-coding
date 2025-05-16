package Model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Issue {

    String issueId;

    String transactionId;

    IssueType issueType;

    String subject;

    String description;

    String email;

    IssueState issueState;

    String resolution;
}
