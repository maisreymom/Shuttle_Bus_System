package com.DaoClasses;

import java.util.List;
import java.util.Map;

import com.EntityClasses.Bus_Per_Schedule;
import com.EntityClasses.Bus_Report_Table;

public interface Driver_Inf {
	public Map<String,Object> checkConfim(String bus_id);
	public String leaveConfim(String bus_per_id);
	public String arrivedConfim(String bus_per_id);
	public List<Bus_Per_Schedule> getdriverSche();
	public List<Map<String, Object>> getDetail(String busPer_id);
	public List<Bus_Per_Schedule> myschedule_driver(String driver_id);
}
