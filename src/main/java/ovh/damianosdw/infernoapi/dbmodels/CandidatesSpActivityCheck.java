/*
 * Created by DamianosDW
 * https://damianosdw.ovh
 */

package ovh.damianosdw.infernoapi.dbmodels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "candidates_sp_activity_check")
public class CandidatesSpActivityCheck
{
    @Id
    @GeneratedValue
    @Column(name = "CHECK_ID", nullable = false, unique = true)
    private int checkId;
    private int userId;
    @Basic
    @Column(nullable = false)
    private LocalDateTime checkDate;
    @Column(nullable = false)
    private int sp1;
    @Column(nullable = false)
    private int sp2;
    @Column(nullable = false)
    private int sp3;
    @Column(nullable = false)
    private int sp4;
    @Column(nullable = false)
    private int sp5;
    @Column(nullable = false)
    private int sp6;
    @Column(nullable = false)
    private int sp7;
    @Column(nullable = false)
    private int sp8;
    @Column(nullable = false)
    private int sp9;
    @Column(nullable = false)
    private int sp10;
    @Column(nullable = false)
    private int sp11;
    @Column(nullable = false)
    private int sp12;
    @Column(nullable = false)
    private int sp13;
    @Column(nullable = false)
    private int sp14;
    @Column(nullable = false)
    private int sp15;
    @Column(nullable = false)
    private int sp16;
    @Column(nullable = false)
    private int sp17;
    @Column(nullable = false)
    private int sp18;
    @Column(nullable = false)
    private int sp19;
    @Column(nullable = false)
    private int sp20;
    @Column(nullable = false)
    private int sp21;
    @Column(nullable = false)
    private int sp22;
    @Column(nullable = false)
    private int sp23;
    @Column(nullable = false)
    private int sp24;
    @Column(nullable = false)
    private int sp25;
    @Column(nullable = false)
    private int sp26;
    @Column(nullable = false)
    private int sp27;
    @Column(nullable = false)
    private int sp28;
    @Column(nullable = false)
    private int sp29;
    @Column(nullable = false)
    private int sp30;
    @Column(nullable = false)
    private int sp31;
    @Column(nullable = false)
    private int sp32;
    @Column(nullable = false)
    private int sp33;
    @Column(nullable = false)
    private int sp34;
    @Column(nullable = false)
    private int sp35;
    @Column(nullable = false)
    private int sp36;
    @Column(nullable = false)
    private int sp37;
    @Column(nullable = false)
    private int sp38;
    @Column(nullable = false)
    private int sp39;
    @Column(nullable = false)
    private int sp40;
    @Column(nullable = false)
    private int sp41;
    @Column(nullable = false)
    private int sp42;
    @Column(nullable = false)
    private int sp43;
    @Column(nullable = false)
    private int sp44;
    @Column(nullable = false)
    private int sp45;
    @Column(nullable = false)
    private int sp46;
    @Column(nullable = false)
    private int sp47;
    @Column(nullable = false)
    private int sp48;
    @Column(nullable = false)
    private int sp49;
    @Column(nullable = false)
    private int sp50;
}
