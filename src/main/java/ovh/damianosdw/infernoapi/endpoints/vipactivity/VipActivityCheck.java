package ovh.damianosdw.infernoapi.endpoints.vipactivity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "vip_activity_check")
public class VipActivityCheck
{
    @Id
    @GeneratedValue
    @Column(name = "CHECK_ID", nullable = false, unique = true)
    private int checkId;
    @ManyToOne
    private int userId;
    @Basic
    @Column(nullable = false)
    private LocalDateTime checkDate;
    @Column(nullable = false)
    private int vip1;
    @Column(nullable = false)
    private int vip2;
    @Column(nullable = false)
    private int vip3;
    @Column(nullable = false)
    private int vip4;
    @Column(nullable = false)
    private int vip5;
    @Column(nullable = false)
    private int vip6;
    @Column(nullable = false)
    private int vip7;
    @Column(nullable = false)
    private int vip8;
    @Column(nullable = false)
    private int vip9;
    @Column(nullable = false)
    private int vip10;
    @Column(nullable = false)
    private int vip11;
    @Column(nullable = false)
    private int vip12;
    @Column(nullable = false)
    private int vip13;
    @Column(nullable = false)
    private int vip14;
    @Column(nullable = false)
    private int vip15;
    @Column(nullable = false)
    private int vip16;
    @Column(nullable = false)
    private int vip17;
    @Column(nullable = false)
    private int vip18;
    @Column(nullable = false)
    private int vip19;
    @Column(nullable = false)
    private int vip20;
    @Column(nullable = false)
    private int vip21;
    @Column(nullable = false)
    private int vip22;
    @Column(nullable = false)
    private int vip23;
    @Column(nullable = false)
    private int vip24;
    @Column(nullable = false)
    private int vip25;
    @Column(nullable = false)
    private int vip26;
    @Column(nullable = false)
    private int vip27;
    @Column(nullable = false)
    private int vip28;
    @Column(nullable = false)
    private int vip29;
    @Column(nullable = false)
    private int vip30;
}
