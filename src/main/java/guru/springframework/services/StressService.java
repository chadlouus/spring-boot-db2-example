package guru.springframework.services;

/**
 * Created by jt on 1/10/17.
 */
public interface StressService {

    void stressInsert(int total);

    void stressDelete(int total);

    void stressRandomGet(int total);

    void stressRandomDelete(int total);
}
