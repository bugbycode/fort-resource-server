package com.bugbycode.module.resource;

import java.io.Serializable;
import java.util.Date;

public class Resource implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	
	private String name;
	
	private String ip;
	
	private int status;
	
	private String type;
	
	private String osType;
	
	private int useSsh;
	
	private int sshPort;
	
	private int useRdp;
	
	private int rdpPort;
	
	private int networkId;
	
	private String networkName;
	
	private Date createTime;
	
	private Date updateTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getOsType() {
		return osType;
	}

	public void setOsType(String osType) {
		this.osType = osType;
	}

	public int getUseSsh() {
		return useSsh;
	}

	public void setUseSsh(int useSsh) {
		this.useSsh = useSsh;
	}

	public int getSshPort() {
		return sshPort;
	}

	public void setSshPort(int sshPort) {
		this.sshPort = sshPort;
	}

	public int getUseRdp() {
		return useRdp;
	}

	public void setUseRdp(int useRdp) {
		this.useRdp = useRdp;
	}

	public int getRdpPort() {
		return rdpPort;
	}

	public void setRdpPort(int rdpPort) {
		this.rdpPort = rdpPort;
	}

	public int getNetworkId() {
		return networkId;
	}

	public void setNetworkId(int networkId) {
		this.networkId = networkId;
	}

	public String getNetworkName() {
		return networkName;
	}

	public void setNetworkName(String networkName) {
		this.networkName = networkName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
