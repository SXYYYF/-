package com.hr.entity;

import com.sun.xml.bind.v2.schemagen.xmlschema.ComplexContent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

/**
 * @author yaoyunfei
 * @data 2021/7/19 9:53
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "comments")
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cid;
    private Long cnid;
    private String cauthor;
    private String ccontent;
    private Date cdate;
    private String cip;
}
