package com.example.Moderateur.services;

//import com.example.Moderateur.client.UserClient;
import com.example.Moderateur.model.Motif;
import com.example.Moderateur.model.ReportedComment;
import com.example.Moderateur.model.User;
import com.example.Moderateur.repository.ModerateurRepository;
import com.example.Moderateur.repository.MotifRepository;
import com.example.Moderateur.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
public class ModerationService {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private ModerateurRepository moderateurRepository;
    @Autowired
    private MotifRepository motifRepository;

    public void verifyUserAge(Long userId) {
        Optional user = userRepository.findById(userId);
        if (user.isPresent()) {
            User us  = (User) user.get();
            if (Period.between(us.getBirthday().toLocalDate(), LocalDate.now()).getYears() < 18) {
                System.out.println("Compte suspendu");

            }
        }


    }

    public void handleReportedComment(ReportedComment comment) {
        if (containsProhibitedContent(comment.getText())) {
            // Appel au service commentaire pour supprimer ou masquer
        }
    }

    public void handleReportedUser(Long userId, String motif) {

        Long idMotif = saveMotif(userId, motif);
        //userClient.suspendUser(userId,idMotif);
        System.out.println("Compte suspendu"+ userId +"Motif " + idMotif);

    }

    private boolean containsProhibitedContent(String text) {
        List<String> bannedWords = List.of("insulte", "haine", "violence");
        return bannedWords.stream().anyMatch(text.toLowerCase()::contains);
    }

    private Long saveMotif(Long userId, String motif) {
        Motif entity = new Motif();
        entity.setId(userId);
        entity.setLabel(motif);

        Motif saved = motifRepository.save(entity);
        return saved.getId();
    }
}

