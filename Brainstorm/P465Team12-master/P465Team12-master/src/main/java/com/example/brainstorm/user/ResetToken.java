//package com.example.brainstorm.user;
//
//import javax.persistence.*;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.GregorianCalendar;
//
//@Entity
//@Table(name = "reset_token")
//public class ResetToken {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "id")
//    private Long id;
//    @Column(name = "token")
//    private String token;
//    @OneToOne(mappedBy = "resetToken")
//    private Users user;
//
//    private Date expiryDate;
//
//    public ResetToken(String token, Users user) {
//        this.token = token;
//        this.user = user;
//        Calendar cal = Calendar.getInstance();
//        Date date = cal.getTime();
//        cal.setTime(date);
//        cal.add(Calendar.HOUR_OF_DAY, 1);
//        this.expiryDate = cal.getTime();
//    }
//
//    public ResetToken() {
//
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getToken() {
//        return token;
//    }
//
//    public void setToken(String token) {
//        this.token = token;
//    }
//
//    public Users getUser() {
//        return user;
//    }
//
//    public void setUser(Users user) {
//        this.user = user;
//    }
//
//    public Date getExpiryDate() { return expiryDate; }
//}
