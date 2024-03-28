/*
 * This code has been automatically generated by aas-core-codegen.
 * Do NOT edit or append.
 */

package aas_core.aas3_0.types.impl;

import aas_core.aas3_0.visitation.IVisitor;
import aas_core.aas3_0.visitation.IVisitorWithContext;
import aas_core.aas3_0.visitation.ITransformer;
import aas_core.aas3_0.visitation.ITransformerWithContext;
import aas_core.aas3_0.types.enums.*;
import aas_core.aas3_0.types.impl.*;
import aas_core.aas3_0.types.model.*;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Objects;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import aas_core.aas3_0.types.model.IReference;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Reference to either a model element of the same or another AAS or to an external
 * entity.
 *
 * <p>A reference is an ordered list of keys.
 *
 * <p>A model reference is an ordered list of keys, each key referencing an element. The
 * complete list of keys may for example be concatenated to a path that then gives
 * unique access to an element.
 *
 * <p>An external reference is a reference to an external entity.
 *
 * <p>Constraints:
 *
 * <ul>
 *   <li> Constraint AASd-121:
 *   For {@link aas_core.aas3_0.types.impl.Reference}'s the value of {@link aas_core.aas3_0.types.impl.Key#getType()} of the first key of "
 *   {@link #getKeys()} shall be one of {@link aas_core.aas3_0.constants.Constants#globallyIdentifiables}.
 *   <li> Constraint AASd-122:
 *   For external references, i.e. {@link aas_core.aas3_0.types.impl.Reference}'s with
 *   {@link #getType()} = {@link aas_core.aas3_0.types.enums.ReferenceTypes#EXTERNAL_REFERENCE}, the value
 *   of {@link aas_core.aas3_0.types.impl.Key#getType()} of the first key of {@link #getKeys()} shall be one of
 *   {@link aas_core.aas3_0.constants.Constants#genericGloballyIdentifiables}.
 *   <li> Constraint AASd-123:
 *   For model references, i.e. {@link aas_core.aas3_0.types.impl.Reference}'s with
 *   {@link #getType()} = {@link aas_core.aas3_0.types.enums.ReferenceTypes#MODEL_REFERENCE}, the value
 *   of {@link aas_core.aas3_0.types.impl.Key#getType()} of the first key of {@link #getKeys()} shall be one of
 *   {@link aas_core.aas3_0.constants.Constants#aasIdentifiables}.
 *   <li> Constraint AASd-124:
 *   For external references, i.e. {@link aas_core.aas3_0.types.impl.Reference}'s with
 *   {@link #getType()} = {@link aas_core.aas3_0.types.enums.ReferenceTypes#EXTERNAL_REFERENCE}, the last
 *   key of {@link #getKeys()} shall be either one of
 *   {@link aas_core.aas3_0.constants.Constants#genericGloballyIdentifiables} or one of
 *   {@link aas_core.aas3_0.constants.Constants#genericFragmentKeys}.
 *   <li> Constraint AASd-125:
 *   For model references, i.e. {@link aas_core.aas3_0.types.impl.Reference}'s with
 *   {@link #getType()} = {@link aas_core.aas3_0.types.enums.ReferenceTypes#MODEL_REFERENCE}, with more
 *   than one key in {@link #getKeys()} the value of {@link aas_core.aas3_0.types.impl.Key#getType()}
 *   of each of the keys following the first
 *   key of {@link #getKeys()} shall be one of {@link aas_core.aas3_0.constants.Constants#fragmentKeys}.
 *
 *   <p>Constraint AASd-125 ensures that the shortest path is used.
 *   <li> Constraint AASd-126:
 *   For model references, i.e. {@link aas_core.aas3_0.types.impl.Reference}'s with
 *   {@link #getType()} = {@link aas_core.aas3_0.types.enums.ReferenceTypes#MODEL_REFERENCE}, with more
 *   than one key in {@link #getKeys()} the value of {@link aas_core.aas3_0.types.impl.Key#getType()}
 *   of the last key in the reference key chain may be
 *   one of {@link aas_core.aas3_0.constants.Constants#genericFragmentKeys} or no key at all
 *   shall have a value out of {@link aas_core.aas3_0.constants.Constants#genericFragmentKeys}.
 *   <li> Constraint AASd-127:
 *   For model references, i.e. {@link aas_core.aas3_0.types.impl.Reference}'s with
 *   {@link #getType()} = {@link aas_core.aas3_0.types.enums.ReferenceTypes#MODEL_REFERENCE}, with more
 *   than one key in {@link #getKeys()} a key with {@link aas_core.aas3_0.types.impl.Key#getType()}
 *   {@link aas_core.aas3_0.types.enums.KeyTypes#FRAGMENT_REFERENCE} shall be preceded by a key with
 *   {@link aas_core.aas3_0.types.impl.Key#getType()} {@link aas_core.aas3_0.types.enums.KeyTypes#FILE} or {@link aas_core.aas3_0.types.enums.KeyTypes#BLOB}. All other
 *   AAS fragments, i.e. {@link aas_core.aas3_0.types.impl.Key#getType()} values
 *   out of {@link aas_core.aas3_0.constants.Constants#aasSubmodelElementsAsKeys}, do not support fragments.
 *
 *   <p>Which kind of fragments are supported depends on the content type and the
 *   specification of allowed fragment identifiers for the corresponding resource
 *   being referenced via the reference.
 *   <li> Constraint AASd-128:
 *   For model references, i.e. {@link aas_core.aas3_0.types.impl.Reference}'s with
 *   {@link #getType()} = {@link aas_core.aas3_0.types.enums.ReferenceTypes#MODEL_REFERENCE}, the
 *   {@link aas_core.aas3_0.types.impl.Key#getValue()} of a {@link aas_core.aas3_0.types.impl.Key} preceded by a {@link aas_core.aas3_0.types.impl.Key} with
 *   {@link aas_core.aas3_0.types.impl.Key#getType()} = {@link aas_core.aas3_0.types.enums.KeyTypes#SUBMODEL_ELEMENT_LIST} is an integer
 *   number denoting the position in the array of the submodel element list.
 * </ul>
 */
public class Reference implements IReference {
  /**
   * Type of the reference.
   *
   * <p>Denotes, whether reference is an external reference or a model reference.
   */
  private ReferenceTypes type;

  /**
   * {@link aas_core.aas3_0.types.model.IHasSemantics#getSemanticId()} of the referenced model element
   * ({@link #getType()} = {@link aas_core.aas3_0.types.enums.ReferenceTypes#MODEL_REFERENCE}).
   *
   * <p>For external references there typically is no semantic ID.
   *
   * <p>It is recommended to use a external reference.
   */
  private IReference referredSemanticId;

  /**
   * Unique references in their name space.
   */
  private List<IKey> keys;

  public Reference(
    ReferenceTypes type,
    List<IKey> keys) {
    this.type = Objects.requireNonNull(
      type,
      "Argument \"type\" must be non-null.");
    this.keys = Objects.requireNonNull(
      keys,
      "Argument \"keys\" must be non-null.");
  }

  public Reference(
    ReferenceTypes type,
    List<IKey> keys,
    IReference referredSemanticId) {
    this.type = Objects.requireNonNull(
      type,
      "Argument \"type\" must be non-null.");
    this.keys = Objects.requireNonNull(
      keys,
      "Argument \"keys\" must be non-null.");
    this.referredSemanticId = referredSemanticId;
  }

  @Override
  public ReferenceTypes getType() {
    return type;
  }

  @Override
  public void setType(ReferenceTypes type) {
    this.type = Objects.requireNonNull(
      type,
      "Argument \"type\" must be non-null.");
  }

  @Override
  public Optional<IReference> getReferredSemanticId() {
    return Optional.ofNullable(referredSemanticId);
  }

  @Override
  public void setReferredSemanticId(IReference referredSemanticId) {
    this.referredSemanticId = referredSemanticId;
  }

  @Override
  public List<IKey> getKeys() {
    return keys;
  }

  @Override
  public void setKeys(List<IKey> keys) {
    this.keys = Objects.requireNonNull(
      keys,
      "Argument \"keys\" must be non-null.");
  }

  /**
   * Iterate recursively over all the class instances referenced from this instance.
   */
  public Iterable<IClass> descend() {
    return new ReferenceRecursiveIterable();
  }

  /**
   * Iterate over all the class instances referenced from this instance.
   */
  public Iterable<IClass> descendOnce() {
    return new ReferenceIterable();
  }

  /**
   * Accept the {@code visitor} to visit this instance for double dispatch.
   **/
  @Override
  public void accept(IVisitor visitor) {
    visitor.visitReference(this);
  }

  /**
   * Accept the {@code visitor} to visit this instance for double dispatch
   * with the {@code context}.
   **/
  @Override
  public <ContextT> void accept(
      IVisitorWithContext<ContextT> visitor,
      ContextT context) {
    visitor.visitReference(this, context);
  }

  /**
   * Accept the {@code transformer} to visit this instance for double dispatch.
   **/
  @Override
  public <T> T transform(ITransformer<T> transformer) {
    return transformer.transformReference(this);
  }

  /**
   * Accept the {@code transformer} to visit this instance for double dispatch
   * with the {@code context}.
   **/
  @Override
  public <ContextT, T> T transform(
      ITransformerWithContext<ContextT, T> transformer,
      ContextT context) {
    return transformer.transformReference(this, context);
  }

  private class ReferenceIterable implements Iterable<IClass> {
    @Override
    public Iterator<IClass> iterator() {
      Stream<IClass> stream = stream();

      return stream.iterator();
    }

    @Override
    public void forEach(Consumer<? super IClass> action) {
      Stream<IClass> stream = stream();

      stream.forEach(action);
    }

    @Override
    public Spliterator<IClass> spliterator() {
      Stream<IClass> stream = stream();

      return stream.spliterator();
    }

    private Stream<IClass> stream() {
      Stream<IClass> memberStream = Stream.empty();

      if (referredSemanticId != null) {
        memberStream = Stream.concat(memberStream,
          Stream.<IClass>of(Reference.this.referredSemanticId));
      }

      if (keys != null) {
        memberStream = Stream.concat(memberStream,
          Reference.this.keys.stream());
      }

      return memberStream;
    }
  }

  private class ReferenceRecursiveIterable implements Iterable<IClass> {
    @Override
    public Iterator<IClass> iterator() {
      Stream<IClass> stream = stream();

      return stream.iterator();
    }

    @Override
    public void forEach(Consumer<? super IClass> action) {
      Stream<IClass> stream = stream();

      stream.forEach(action);
    }

    @Override
    public Spliterator<IClass> spliterator() {
      Stream<IClass> stream = stream();

      return stream.spliterator();
    }

    private Stream<IClass> stream() {
      Stream<IClass> memberStream = Stream.empty();

      if (referredSemanticId != null) {
        memberStream = Stream.concat(memberStream,
          Stream.concat(Stream.<IClass>of(Reference.this.referredSemanticId),
            StreamSupport.stream(Reference.this.referredSemanticId.descend().spliterator(), false)));
      }

      if (keys != null) {
        memberStream = Stream.concat(memberStream,
          Reference.this.keys.stream()
            .flatMap(item -> Stream.concat(Stream.<IClass>of(item),
              StreamSupport.stream(item.descend().spliterator(), false))));
      }

      return memberStream;
    }
  }
}

/*
 * This code has been automatically generated by aas-core-codegen.
 * Do NOT edit or append.
 */
