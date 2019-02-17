package com.perennial.patientapp.vo;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tracker_log")
public class TrackerVO implements IGenericVO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "tracker_id")
    private long id;

    @Column(name="schedule_id")
    private long schedulerId;

    @Column(name="is_taken")
    private int isTaken;

    @Column(name="is_panic")
    private long isPanic;

    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    public TrackerVO(long schedulerId, Date createdDate) {
        this.schedulerId = schedulerId;
        this.createdDate = createdDate;
    }
}
