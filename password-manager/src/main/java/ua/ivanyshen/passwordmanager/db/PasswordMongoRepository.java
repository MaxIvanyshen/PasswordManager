package ua.ivanyshen.passwordmanager.db;

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
import ua.ivanyshen.passwordmanager.entities.Password;
import ua.ivanyshen.passwordmanager.entities.User;

public class PasswordMongoRepository implements Repository<Password> {


    private MongoCollection<Document> usersCollection;
    private MongoClient mongoClient;
    private int size = 0;
    public PasswordMongoRepository(String uri) {

        try {
            mongoClient = MongoClients.create(uri);
            MongoDatabase database = mongoClient.getDatabase("password_manager");

            try {
                Bson command = new BsonDocument("ping", new BsonInt64(1));
                Document commandResult = database.runCommand(command);
                System.out.println("Successfully connected");

                usersCollection = database.getCollection("passwords");
            } catch (MongoException me) {
                System.out.println("Connection failed");
            }
        } catch (MongoException me) {
            System.out.println("couldnt create MongoClient");
        }

    }

    private void save(Password pw) {
        size++;
        Document userDoc = new Document("_id", pw.getId())
                .append("url", pw.getUrl())
                .append("notes", pw.getNotes())
                .append("password", pw.getPassword());
        usersCollection.insertOne(userDoc);
    }

    @Override
    public Password insert(Password element) {
        Password found = findById(element.getId());
        if(found != null) {
            found.setPassword(element.getPassword());
            found.setUrl(element.getUrl());
            found.setNotes(element.getNotes());
            delete(found.getId());
            save(found);
            return found;
        }
        save(element);
        return element;
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
    public Password findById(String id) {
        Bson filter = Filters.eq("_id", id);
        Document found = usersCollection.find(filter).first();
        if(found == null) return null;
        Password pw = new Password((String) found.get("url"), (String) found.get("password"));
        pw.setId(id);
        return pw;
    }
}
