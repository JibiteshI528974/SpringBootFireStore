package com.example.FireStoreSpringBoot.Service;

import com.example.FireStoreSpringBoot.Component.User;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class UserService {
    private final Firestore firestore;

    public UserService(Firestore firestore) {
        this.firestore = firestore;
    }

    public User addUser(User user) throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> future = firestore.collection("users").document(user.getId()).set(user);
        future.get(); // Wait for write operation
        return user;
    }

    public List<User> getUsers() throws ExecutionException, InterruptedException {
        return firestore.collection("users").get().get().toObjects(User.class);
    }

    public User getUserById(String id) throws ExecutionException, InterruptedException {
        DocumentReference docRef = firestore.collection("users").document(id);
        DocumentSnapshot document = docRef.get().get();
        return document.exists() ? document.toObject(User.class) : null;
    }

    public void updateUserById(String id, User user) throws ExecutionException, InterruptedException {
        firestore.collection("users").document(id).set(user).get();
    }
}
