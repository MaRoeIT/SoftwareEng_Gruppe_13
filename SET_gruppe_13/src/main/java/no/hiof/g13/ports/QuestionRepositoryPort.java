package no.hiof.g13.ports;

import no.hiof.g13.models.Question;

public interface QuestionRepositoryPort {

    Question getQuestionById(int questionId);
}
