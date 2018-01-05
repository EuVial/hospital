//package com.epam.dao;
//
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.junit.runners.Parameterized;
//
//import java.io.Serializable;
//import java.util.List;
//
//@RunWith(Parameterized.class)
//public abstract class GenericDaoTest<Context> {
//    /**
//     * Class of the tested DAO object.
//     */
//    protected Class daoClass;
//
//    /**
//     * An instance of a domain object that doesn't have a record in storage
//     * system.
//     */
//    protected Identified notPersistedDto;
//
//    /**
//     * An instance of the tested DAO object.
//     * @return
//     */
//    public abstract GenericDao dao();
//
//    /**
//     * Context of interaction with the data storage system.
//     * @return
//     */
//    public abstract Context context();
//
//    @Test
//    public void testCreate() throws PersistException {
//        Identified dto = dao().create();
//        Assert.assertNotNull(dto);
//        Assert.assertNotNull(dto.getId());
//    }
//
//    @Test
//    public void testPersist() throws PersistException {
//        Assert.assertNull(notPersistedDto.getId());
//        notPersistedDto = dao().persist(notPersistedDto);
//        Assert.assertNotNull(notPersistedDto.getId());
//    }
//
//    @Test
//    public void testGetByPK() throws PersistException {
//        Serializable id = dao().create().getId();
//        Identified dto = dao().getByPK(id);
//        Assert.assertNotNull(dto);
//    }
//
//    @Test
//    public void testDelete() throws PersistException {
//        Identified dto = dao().create();
//        Assert.assertNotNull(dto);
//
//        List list = dao().getAll();
//        Assert.assertNotNull(list);
//
//        int oldSize = list.size();
//        Assert.assertTrue(oldSize > 0);
//
//        dao().delete(dto);
//
//        list = dao().getAll();
//        Assert.assertNotNull(list);
//
//        int newSize = list.size();
//        Assert.assertEquals(1, oldSize - newSize);
//    }
//
//    @Test
//    public void testGetAll() throws PersistException {
//        List list = dao().getAll();
//        Assert.assertNotNull(list);
//        Assert.assertTrue(list.size() > 0);
//    }
//
//    public GenericDaoTest(Class clazz, Identified<Integer> notPersistedDto) {
//        this.daoClass = clazz;
//        this.notPersistedDto = notPersistedDto;
//    }
//}