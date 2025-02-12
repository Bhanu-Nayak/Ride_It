//package com.rideit.pojo;
//
//import java.math.BigDecimal;
//
////package com.example.bikerental.entity;
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.Table;
//
//@Entity
//@Table(name = "damage_reports")
//public class DamageReport extends BaseEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long reportId;
//
//    @ManyToOne
//    @JoinColumn(name = "booking_id", nullable = false)
//    private Booking booking;
//
//    @Column(columnDefinition = "JSON", nullable = false)
//    private String damageImages; // JSON array of image URLs
//
//    @Column(nullable = false)
//    private String description;
//
//    @Column(nullable = false)
//    private String reportedBy; // customer or owner
//
//    @Column(precision = 10, scale = 2)
//    private BigDecimal repairEstimate;
//
//    @Column(nullable = false)
//    private String status = "under_review"; // under_review, resolved
//
//    private String adminComments;
//}
