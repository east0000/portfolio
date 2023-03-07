package com.example.mymemo.database;

import java.util.List;

public interface MemoDatabase {
    public abstract void submitOne(DtMemo memo);
    public abstract List<DtMemo> searchSome(DtMemo query);
    //public abstract void FileDelete();
}
