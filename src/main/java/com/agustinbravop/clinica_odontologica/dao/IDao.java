package com.agustinbravop.clinica_odontologica.dao;

import java.util.List;
import java.util.Optional;

public interface IDao<T> {
    T guardar(T t);
    Optional<T> buscar(Integer id);
    List<T> buscarTodos();
    void eliminar(Integer id);
    T actualizar(T t);
}
