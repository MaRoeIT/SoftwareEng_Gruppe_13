package no.hiof.g13.adapters;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import no.hiof.g13.DTO.QuestionDTO;
import no.hiof.g13.models.Question;
import no.hiof.g13.ports.out.QuestionRepositoryPort;

public class QuestionRepositoryMySQL implements QuestionRepositoryPort{

    @Override
    public Question getQuestionById(int questionId) {
        try(Connection connection = MySQLAdapter.getConnection()) {
            QuestionDTO dto = fetchQuestion(connection, questionId);
            return dto.toDomain();
        }
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return new Question();
    }

    public QuestionDTO fetchQuestion(Connection connection, int questionId) {

        String mySQL_scripts = """
                SELECT q.question_id, q.question_description, q.choices_id, qc.choice1, qc.choice2, qc.choice3, qc.choice4
                FROM question q
                INNER JOIN question_choices qc ON qc.choicesId = q.choices_id
                WHERE q.question_id = ?
                """;

        try(PreparedStatement preparedStatement = connection.prepareStatement(mySQL_scripts)) {
            preparedStatement.setInt(1, questionId);

            try(ResultSet rs = preparedStatement.executeQuery()) {
                if(rs.next()) {
                    List<String> choices = new ArrayList<>();
                    choices.add(rs.getString("choice1"));
                    choices.add(rs.getString("choice2"));
                    choices.add(rs.getString("choice3"));
                    choices.add(rs.getString("choice4"));

                    return new QuestionDTO(
                            rs.getInt("question_id"), rs.getString("question_description"),
                            rs.getInt("choices_id"), choices
                    );
                }
            }
            throw new RuntimeException("No question found with ID " + questionId);

        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return new QuestionDTO();
    }

}