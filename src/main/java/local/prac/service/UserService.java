package local.prac.service;

import local.prac.entity.AppUser;
import local.prac.repo.AppUserRepo;
import org.springframework.stereotype.Service;

@Service
public class UserService extends AbstractService<AppUser, Long, AppUserRepo> {

    private AppUserRepo userRepo;

    public UserService(AppUserRepo repo) {
        super(repo);
        userRepo = repo;
    }

    @Override
    public Long getId(AppUser user) {
        return user.getUid();
    }
}
