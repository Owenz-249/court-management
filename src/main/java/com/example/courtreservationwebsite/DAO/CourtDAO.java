package com.example.courtreservationwebsite.DAO;

import com.example.courtreservationwebsite.Model.Court;
import com.example.courtreservationwebsite.Model.CourtAvailability;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class CourtDAO extends DbConnection{

    public List<Court> searchCourts(int kipTime, String courtType, String dayBegin, String dayEnd) {
        List<Court> searchResults = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT c.*, ca.* " +
                             "FROM court c " +
                             "JOIN court_availability ca ON c.id = ca.court_id " +
                             "WHERE ca.kip_time = ? AND c.type = ? AND ca.day_available BETWEEN ? AND ?")) {

            preparedStatement.setInt(1, kipTime);
            preparedStatement.setString(2, courtType);
            preparedStatement.setString(3, dayBegin);
            preparedStatement.setString(4, dayEnd);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                Map<Integer, Court> courtMap = new HashMap<>();

                while (resultSet.next()) {
                    int courtId = resultSet.getInt("id");

                    if (!courtMap.containsKey(courtId)) {
                        Court court = new Court();
                        court.setId(courtId);
                        court.setName(resultSet.getString("name"));
                        court.setType(resultSet.getString("type"));
                        court.setFee(resultSet.getInt("fee"));

                        courtMap.put(courtId, court);
                        searchResults.add(court);
                    }

                    CourtAvailability availability = new CourtAvailability();
                    availability.setId(resultSet.getInt("ca.id"));
                    availability.setKipTime(resultSet.getInt("ca.kip_time"));
                    availability.setDayAvailable(resultSet.getDate("ca.day_available"));
                    availability.setStatus(resultSet.getInt("ca.status"));

                    courtMap.get(courtId).getAvailabilityList().add(availability);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }

        return searchResults;
    }


}
