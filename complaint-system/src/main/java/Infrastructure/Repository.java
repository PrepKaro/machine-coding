package Infrastructure;

import Model.Issue;
import Model.IssueState;
import Model.IssueType;

import java.util.List;
import java.util.Map;

public interface Repository {

    String createIssue(String transactionId, IssueType issueType, String subject,
                       String description, String email);

    String addAgent(String agentEmail, String agentName ,List<IssueType> issueType);

    String assignIssue(String issueId);

    List<Issue> getIssueByType(IssueType issueType);

    List<Issue> getIssueByUserEmail(String email);

    boolean updateIssue(String issueId, IssueState status, String resolution);

    boolean resolveIssue(String issueId, String resolution);

    void viewAgentsWorkHistory();
}
