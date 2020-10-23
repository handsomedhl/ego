package pojo;

import java.util.Date;

public class Borrow extends BorrowKey {
    private Date borrowTime;

    private Date shouldRTime;

    private Boolean isReturn;

    private Date returnTime;

    public Date getBorrowTime() {
        return borrowTime;
    }

    public void setBorrowTime(Date borrowTime) {
        this.borrowTime = borrowTime;
    }

    public Date getShouldRTime() {
        return shouldRTime;
    }

    public void setShouldRTime(Date shouldRTime) {
        this.shouldRTime = shouldRTime;
    }

    public Boolean getIsReturn() {
        return isReturn;
    }

    public void setIsReturn(Boolean isReturn) {
        this.isReturn = isReturn;
    }

    public Date getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(Date returnTime) {
        this.returnTime = returnTime;
    }
}