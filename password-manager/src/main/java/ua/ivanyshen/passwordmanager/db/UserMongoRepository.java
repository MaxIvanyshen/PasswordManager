package ua.ivanyshen.passwordmanager.db;

import com.mongodb.DBObject;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.BsonDocument;
import org.bson.BsonInt64;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import ua.ivanyshen.passwordmanager.entities.User;

public class UserMongoRepository implements Repository<User> {

    private MongoCollection<Document> usersCollection;
    private MongoClient mongoClient;
    private int size = 0;
    public UserMongoRepository(String uri) {

        try {
            mongoClient = MongoClients.create(uri);
            MongoDatabase database = mongoClient.getDatabase("password_manager");

            try {
                Bson command = new BsonDocument("ping", new BsonInt64(1));
                Document commandResult = database.runCommand(command);
                System.out.println("Successfully connected");

                usersCollection = database.getCollection("users");
            } catch (MongoException me) {
                System.out.println("Connection failed");
            }
        } catch (MongoException me) {
            System.out.println("couldnt create MongoClient");
        }

    }

    @Override
    public User insert(User element) {
        User found = findById(element.getId());
        if(found != null) {
            found.setMasterPassword(element.getMasterPassword());
            found.setEmail(element.getEmail());
            found.setUsername(element.getUsername());
            delete(found.getId());
            save(found);
            return found;
        }
        save(element);
        return element;
    }

    private void save(User u) {
        size++;
        Document userDoc = new Document("_id", u.getId())
                .append("email", u.getEmail())
                .append("username", u.getUsername())
                .append("masterPassword", u.getMasterPassword());
        usersCollection.insertOne(userDoc);
    }

    @Override
    public void delete(Object o) {
        size--;
        String id = (String) o;
        Bson filter = Filters.eq("_id", id);
        usersCollection.deleteOne(filter);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public User findById(String id) {
        Bson filter = Filters.eq("_id", id);
        Document found = usersCollection.find(filter).first();
        if(found == null) return null;
        User u = new User((String) found.get("email"), (String) found.get("username"), (String) found.get("masterPassword"));
        u.setId(id);
        return u;
    }
}
