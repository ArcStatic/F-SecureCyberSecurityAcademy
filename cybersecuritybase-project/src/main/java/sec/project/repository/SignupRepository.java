package sec.project.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;
import sec.project.domain.Signup;

public interface SignupRepository extends JpaRepository<Signup, Long> {

    //Signup findByName(String name);
    
    List<Signup> findByName(String name);
    
}
