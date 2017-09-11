package lodz.uni.portal.form;

import javax.validation.constraints.Size;

public class SingleGameForm {
    private String opponentName;

    private String loggedInUserResult;

    private String opponentResult;

    private String description;

    public String getOpponentName() {
        return opponentName;
    }

    public void setOpponentName(String opponentName) {
        this.opponentName = opponentName;
    }

    public String getLoggedInUserResult() {
        return loggedInUserResult;
    }

    public void setLoggedInUserResult(String loggedInUserResult) {
        this.loggedInUserResult = loggedInUserResult;
    }

    public String getOpponentResult() {
        return opponentResult;
    }

    public void setOpponentResult(String opponentResult) {
        this.opponentResult = opponentResult;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
