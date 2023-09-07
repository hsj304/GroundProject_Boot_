package com.sist.web.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Setter;

import lombok.Getter;

@Entity
@Table(name = "ground")
@Getter@Setter
public class GroundEntity {
	@Id
	private int gno;
	private int hit;
	private int gprice;
	private String gname, gaddr, gimage, gnotice, football_reserve_day;
}
