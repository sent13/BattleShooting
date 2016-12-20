package com.plplsent.battleshooting.Utils;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ObjectStream<T extends Serializable> {
    File file;

    public ObjectStream(File file) {
        this.file = file;
        fileCheck();
    }
    private void fileCheck(){
        if (!file.exists()) {
            try {
                file.createNewFile();
            }catch (IOException e){
                e.printStackTrace();
            }
        }

    }

    /**
     * 要素を既存データに追加後保存します
     * @param data
     */
    public void save(T data) {
        fileCheck();
        List<T> dataList = read();
        dataList.add(data);
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(dataList);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 全データを読み込み、返します
     * @return 保存されているすべてのデータ
     */
    public List<T> read(){
        fileCheck();
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));

            return (List<T>) ois.readObject();
        }catch (EOFException e){
            return new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    /**
     * Create new instance after clear() or throw IllegalStateException()
     */
    public void clear() {
        file.delete();
    }
}

