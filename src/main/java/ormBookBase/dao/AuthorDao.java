package ormBookBase.dao;

import ormBookBase.dto.Author;

import java.util.List;

public interface AuthorDao {

    void addAuthors(List<Author> authorsList);
}
