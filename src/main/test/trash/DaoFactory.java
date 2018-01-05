//package com.epam.dao;
//
//public interface DaoFactory<Context> {
//
//    public interface DaoCreator<Context> {
//        public GenericDao create(Context context);
//    }
//
//    /**
//     * @return connection to database.
//     * @throws PersistException
//     */
//    public Context getContext() throws PersistException;
//
//    /**
//     *
//     * @param context
//     * @param dtoClass
//     * @return object to manage the persist state of an object.
//     * @throws PersistException
//     */
//    public GenericDao getDao(Context context, Class dtoClass)
//            throws PersistException;
//}
