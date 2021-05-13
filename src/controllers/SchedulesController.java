package controllers;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;

import Models.Schedule;

public class SchedulesController {
	
	public static long getDifferentBetweenTwoTimes(String time1, String time2){
		
		LocalTime t1 = LocalTime.parse(time1);
        LocalTime t2 = LocalTime.parse(time2);
        Duration diff = Duration.between(t2, t1);
		
        System.out.println(diff.toSeconds());
		
		return diff.toSeconds();
	}
	
	public static void writeSchedulesArc (ArrayList<String> schedules) {
		
		ArrayList<Schedule> listSchedules = new ArrayList<>();
		String fullText = Archiver.lerArquivo("src/data/Schedules.txt");
		
		json.JSONArray jA = new json.JSONArray(fullText);
		for (int i = 0; i < jA.length(); i++) {
			Schedule schedule= new Schedule(jA.getJSONObject(i));
			listSchedules.add(schedule);
		}
		
		for (int i = 0; i < schedules.size()-1; i++) {
			Schedule schedule = new Schedule();
			schedule.setTipo(0);
			schedule.setHorario(schedules.get(i));
			listSchedules.add(schedule);
		}
		
		Schedule schedule = new Schedule();
		schedule.setTipo(1);
		schedule.setHorario(schedules.get(schedules.size()-1));
		listSchedules.add(schedule);
		
		json.JSONArray jsArray = new json.JSONArray();
		for (Schedule l : listSchedules) {
			jsArray.put(l.toJson());
		}
		Archiver.gravarArquivo("src/data/Schedules.txt", jsArray);
	}
	
	public static void writeScheduleArc (String sche) {
		
		ArrayList<Schedule> listSchedules = new ArrayList<>();
		String fullText = Archiver.lerArquivo("src/data/Schedules.txt");
		
		json.JSONArray jA = new json.JSONArray(fullText);
		for (int i = 0; i < jA.length(); i++) {
			Schedule schedule = new Schedule(jA.getJSONObject(i));
			listSchedules.add(schedule);
		}
		
		Schedule schedule = new Schedule();
		schedule.setTipo(1);
		schedule.setHorario(sche);
		listSchedules.add(schedule);
		
		json.JSONArray jsArray = new json.JSONArray();
		for (Schedule l : listSchedules) {
			jsArray.put(l.toJson());
		}
		Archiver.gravarArquivo("src/data/Schedules.txt", jsArray);
	}

}
