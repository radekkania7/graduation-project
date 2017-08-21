package lodz.uni.portal.form;

import javax.validation.constraints.Size;

public class SingleGameForm {
    private String opponentName;

    @Size(min = 1)
    private Integer loggedInUserResult;

    @Size(min = 1)
    private Integer opponentResult;

    @Size(min = 1, max = 250)
    private String description;

    public String getOpponentName() {
        return opponentName;
    }

    public void setOpponentName(String opponentName) {
        this.opponentName = opponentName;
    }

    public Integer getLoggedInUserResult() {
        return loggedInUserResult;
    }

    public void setLoggedInUserResult(Integer loggedInUserResult) {
        this.loggedInUserResult = loggedInUserResult;
    }

    public Integer getOpponentResult() {
        return opponentResult;
    }

    public void setOpponentResult(Integer opponentResult) {
        this.opponentResult = opponentResult;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
