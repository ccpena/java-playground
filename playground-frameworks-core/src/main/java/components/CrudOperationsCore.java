package components;

public interface CrudOperationsCore<T> {
  T create(T input);

  T update(T input);

  void delete(Long id);

  T read(Long id);
}
