/*
 * Created by DamianosDW
 * https://damianosdw.ovh
 */

package ovh.damianosdw.infernoapi.dbmodels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "important_info")
public class ImportantInfo
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "INFO_ID")
    private int infoId;
    @Column(name = "info_type", nullable = false)
    private String infoType;
    @Column(name = "content", nullable = false)
    @Type(type = "text")
    private String content;
}
