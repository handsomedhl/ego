package pojo;

import java.util.Date;

public class Amerce {
    private Integer amerceId;

    private Integer borrowId;

    private String name;

    private String bookName;

    private String bookId;

    private Date borrowTime;

    private Date shouldRTime;

    private Date returnTime;

    private Double fines;

    public Integer getAmerceId() {
        return amerceId;
    }

    public void setAmerceId(Integer amerceId) {
        this.amerceId = amerceId;
    }

    public Integer getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(Integer borrowId) {
        this.borrowId = borrowId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName == null ? null : bookName.trim();
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId == null ? null : bookId.trim();
    }

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

    public Date getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(Date returnTime) {
        this.returnTime = returnTime;
    }

    public Double getFines() {
        return fines;
    }

    public void setFines(Double fines) {
        this.fines = fines;
    }
}