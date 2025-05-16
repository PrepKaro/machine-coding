package service;

import Infrastructure.InMemoryRepository;
import Model.IssueState;
import Model.IssueType;

import java.util.Arrays;

public class Driver {

    public static void main(String[] args) {
        System.out.println("Welcome to Complain System");

        InMemoryRepository inMemoryRepository = new InMemoryRepository();


        inMemoryRepository.createIssue("T1", IssueType.PAYMENT, "Payment Failed",
                "My payment failed but money is debited", "testUser1@test.com");
        inMemoryRepository.createIssue("T2", IssueType.MUTUAL_FUND, "Purchase Failed",
                "Unable to purchase Mutual Fund", "testUser2@test.com");
        inMemoryRepository.createIssue("T3", IssueType.PAYMENT, "Payment Failed",
                "My payment failed but money is debited", "testUser2@test.com");

        inMemoryRepository.addAgent("agent1@test.com", "" , Arrays.asList(IssueType.PAYMENT, IssueType.GOLD));
        inMemoryRepository.addAgent("agent2@test.com", "" , Arrays.asList(IssueType.MUTUAL_FUND));


        inMemoryRepository.assignIssue("I1");
        inMemoryRepository.assignIssue("I2");
        inMemoryRepository.assignIssue("I3");

        inMemoryRepository.getIssueByUserEmail("testUser2@test.com");
        inMemoryRepository.getIssueByType(IssueType.PAYMENT);

        inMemoryRepository.updateIssue("I3", IssueState.IN_PROGRESS, "Waiting for payment confirmation");

        inMemoryRepository.resolveIssue("I3", "PaymentFailed debited amount will get reversed");

        inMemoryRepository.viewAgentsWorkHistory();
    }
}
