/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev.user.model;

import com.journaldev.jsf.util.DataConnect;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author RN00468025
 */
@ManagedBean(name="track_journey")
@SessionScoped
public class TrackJourney implements Serializable{
   public List<JourneyTrack> getJourneyDetails() throws SQLException
    {
   Connection con = DataConnect.getConnection();
   if(con==null)
   throw new SQLException("Can't get database connection");
   PreparedStatement ps = con.prepareStatement("SELECT journey_master.vehicle_no,route_master.route_1,route_master.route_2,route_master.route_3,route_master.route_4 FROM route_master INNER JOIN journey_master ON journey_master.route_id = route_master.route_id where journey_master.vehicle_no=1");
   ResultSet rs = ps.executeQuery();
   List<JourneyTrack> list = new ArrayList<JourneyTrack>();
   
   while(rs.next())
   {
       JourneyTrack journey = new JourneyTrack();
       journey.setVehicle_no(rs.getString("vehicle_no"));
       System.out.println(rs.getString(1));
       journey.setRoute1(rs.getString("route1"));
       journey.setRoute2(rs.getString("route2"));
       journey.setRoute3(rs.getString("route3"));
       journey.setRoute4(rs.getString("route4"));
       list.add(journey);
   }
    return list;
    }
   
   public String add() {
       int count=0;
       Connection con;
       ResultSet rs;
        try {
     con = DataConnect.getConnection();
   PreparedStatement ps = con.prepareStatement("SELECT journey_master.vehicle_no,route_master.route_1,route_master.route_2,route_master.route_3,route_master.route_4 FROM route_master INNER JOIN journey_master ON journey_master.route_id = route_master.route_id where journey_master.vehicle_no=1");
   rs = ps.executeQuery();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
               
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (count == 0) {
            	FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Journey",
							"Success"));
			return "track";
        } else {
            FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"City Alreay Exist or Some Error Occurred",
							"invalid"));
            return "invalid";
        }
    }
   
   
}
