package com.gson.objects;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Web {
	

	@SerializedName("Results")
	public List<Results> results;
	
	
	public List<Results> getResults() {
		return results;
	}

	public void setResults(List<Results> results) {
		this.results = results;
	}
	
	@SerializedName("Total")
	public String total;
	
	@SerializedName("Offset")
	public String offset;
	

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getOffset() {
		return offset;
	}

	public void setOffset(String offset) {
		this.offset = offset;
	}

	

}
