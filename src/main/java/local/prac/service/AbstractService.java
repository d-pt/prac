package local.prac.service;

import com.google.common.base.Preconditions;
import local.prac.exception.AppNoRecordFound;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public class AbstractService<E, I, R extends JpaRepository<E, I>> {

    private R repo;

    public AbstractService(R repo) {
        this.repo = repo;
    }

    public List<E> findAll() {
        return repo.findAll();
    }

    public E findById(I id) {
        Preconditions.checkNotNull(id);
        return repo.findById(id).orElseThrow(AppNoRecordFound::new);
    }

    public E add(E e) {
        Preconditions.checkNotNull(e);
        return repo.save(e);
    }

    public void update(E e) {
        Preconditions.checkNotNull(e);
        //findById(e.getUid());
        repo.save(e);
    }

    public void delete(I id) {
        Preconditions.checkNotNull(id);
        repo.delete(findById(id));
    }
}
