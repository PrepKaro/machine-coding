package Model;

public enum IssueType {
    PAYMENT("Payment-related services"),
    MUTUAL_FUND("Mutual fund-related services"),
    GOLD("Gold-related services"),
    INSURANCE("Insurance-related services");

    private final String issueType;

    IssueType(String issueType) {
        this.issueType = issueType;
    }

    public String getIssueType() {
        return issueType;
    }


    public static IssueType fromIssueType(String issueType) {
        for (IssueType type : IssueType.values()) {
            if (type.getIssueType().equalsIgnoreCase(issueType)) {
                return type;
            }
        }
        throw new IllegalArgumentException("No matching enum for description: " + issueType);
    }
}
