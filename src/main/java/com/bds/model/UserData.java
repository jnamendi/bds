package com.bds.model;

import static javax.persistence.GenerationType.SEQUENCE;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user_data", schema = "bds_bloggers")
public class UserData {
    @Id
    @SequenceGenerator(name = "user_data_id_seq", sequenceName = "user_data_id_seq", allocationSize = 1, schema = "bds_bloggers")
    @GeneratedValue(strategy = SEQUENCE, generator = "user_data_id_seq")
	private Integer Id;
    @Column(name = "user_name")
	private String userName;
    @Column(name = "password")
	private String password;
}
