package edu.gpnu.entity;

public class Follow {

    /**
     *拥有粉丝的人的studentId
     */
    private String studentId;

    public Follow(String studentId, String follower) {
        this.studentId = studentId;
        this.follower = follower;
    }

    /**
     *粉丝的studentId
     */
    private String follower;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getFollower() {
        return follower;
    }

    public void setFollower(String follower) {
        this.follower = follower;
    }
}
