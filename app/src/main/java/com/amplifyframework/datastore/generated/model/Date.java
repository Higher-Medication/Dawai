package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.temporal.Temporal;
import com.amplifyframework.core.model.annotations.HasMany;

import java.util.List;
import java.util.UUID;
import java.util.Objects;

import androidx.core.util.ObjectsCompat;

import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.annotations.Index;
import com.amplifyframework.core.model.annotations.ModelConfig;
import com.amplifyframework.core.model.annotations.ModelField;
import com.amplifyframework.core.model.query.predicate.QueryField;

import static com.amplifyframework.core.model.query.predicate.QueryField.field;

/** This is an auto generated class representing the Date type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Dates")
public final class Date implements Model {
  public static final QueryField ID = field("Date", "id");
  public static final QueryField DATE = field("Date", "date");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="AWSDate", isRequired = true) Temporal.Date date;
  private final @ModelField(targetType="Medicine") @HasMany(associatedWith = "user", type = Medicine.class) List<Medicine> meds = null;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  public String getId() {
      return id;
  }
  
  public Temporal.Date getDate() {
      return date;
  }
  
  public List<Medicine> getMeds() {
      return meds;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private Date(String id, Temporal.Date date) {
    this.id = id;
    this.date = date;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Date date = (Date) obj;
      return ObjectsCompat.equals(getId(), date.getId()) &&
              ObjectsCompat.equals(getDate(), date.getDate()) &&
              ObjectsCompat.equals(getCreatedAt(), date.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), date.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getDate())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Date {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("date=" + String.valueOf(getDate()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()))
      .append("}")
      .toString();
  }
  
  public static DateStep builder() {
      return new Builder();
  }
  
  /** 
   * WARNING: This method should not be used to build an instance of this object for a CREATE mutation.
   * This is a convenience method to return an instance of the object with only its ID populated
   * to be used in the context of a parameter in a delete mutation or referencing a foreign key
   * in a relationship.
   * @param id the id of the existing item this instance will represent
   * @return an instance of this model with only ID populated
   * @throws IllegalArgumentException Checks that ID is in the proper format
   */
  public static Date justId(String id) {
    try {
      UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
    } catch (Exception exception) {
      throw new IllegalArgumentException(
              "Model IDs must be unique in the format of UUID. This method is for creating instances " +
              "of an existing object with only its ID field for sending as a mutation parameter. When " +
              "creating a new object, use the standard builder method and leave the ID field blank."
      );
    }
    return new Date(
      id,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      date);
  }
  public interface DateStep {
    BuildStep date(Temporal.Date date);
  }
  

  public interface BuildStep {
    Date build();
    BuildStep id(String id) throws IllegalArgumentException;
  }
  

  public static class Builder implements DateStep, BuildStep {
    private String id;
    private Temporal.Date date;
    @Override
     public Date build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new Date(
          id,
          date);
    }
    
    @Override
     public BuildStep date(Temporal.Date date) {
        Objects.requireNonNull(date);
        this.date = date;
        return this;
    }
    
    /** 
     * @param id id
     * @return Current Builder instance, for fluent method chaining
     */
    public BuildStep id(String id) {
        this.id = id;
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String id, Temporal.Date date) {
      super.id(id);
      super.date(date);
    }
    
    @Override
     public CopyOfBuilder date(Temporal.Date date) {
      return (CopyOfBuilder) super.date(date);
    }
  }
  
}
