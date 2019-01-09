package local.prac.service;

import com.google.common.base.Preconditions;
import local.prac.entity.AppUser;
import local.prac.repo.AppUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceSpecific {

    @Autowired
    private AppUserRepo userRepo;

    public AppUser findById(Long userId) {
        Preconditions.checkNotNull(userId);
        Preconditions.checkArgument(userId > 0);
        return userRepo.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException());
    }

    public void add(AppUser user) {
        Preconditions.checkNotNull(user);
        userRepo.save(user);
    }

    public void update(AppUser user) {
        Preconditions.checkNotNull(user);
        findById(user.getUid());
        userRepo.save(user);
    }

    public void delete(Long userId) {
        Preconditions.checkNotNull(userId);
        userRepo.delete(findById(userId));
    }
}
