package com.hr.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "news")
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long nid;
    private long ntid;
    @Column(name = "ntitle")
    private String ntitle;
    @Column(name = "ncreateDate")
    private Date ncreateDate;
    @Column(name = "nauthor")
    private String nauthor;
    @Column(name = "ncontent")
    private String ncontent;
    @Column(name = "nsummary")
    private String nsummary;
    @Column(name = "nmodifyDate")
    private Date nmodifyDate;
    @Transient
    private List<Comments> commentsList;


}