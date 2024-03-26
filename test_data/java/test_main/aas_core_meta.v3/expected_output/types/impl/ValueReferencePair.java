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
import aas_core.aas3_0.types.model.IValueReferencePair;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * A value reference pair within a value list. Each value has a global unique id
 * defining its semantic.
 */
public class ValueReferencePair implements IValueReferencePair {
  /**
   * The value of the referenced concept definition of the value in {@link ValueReferencePair#getValueId valueId}.
   */
  private String value;

  /**
   * Global unique id of the value.
   *
   * <p>It is recommended to use a global reference.
   */
  private IReference valueId;

  public ValueReferencePair(
    String value,
    IReference valueId) {
    this.value = Objects.requireNonNull(
      value,
      "Argument \"value\" must be non-null.");
    this.valueId = Objects.requireNonNull(
      valueId,
      "Argument \"valueId\" must be non-null.");
  }

  @Override
  public String getValue() {
    return value;
  }

  @Override
  public void setValue(String value) {
    this.value = Objects.requireNonNull(
      value,
      "Argument \"value\" must be non-null.");
  }

  @Override
  public IReference getValueId() {
    return valueId;
  }

  @Override
  public void setValueId(IReference valueId) {
    this.valueId = Objects.requireNonNull(
      valueId,
      "Argument \"valueId\" must be non-null.");
  }

  /**
   * Iterate recursively over all the class instances referenced from this instance.
   */
  public Iterable<IClass> descend() {
    return new ValueReferencePairRecursiveIterable();
  }

  /**
   * Iterate over all the class instances referenced from this instance.
   */
  public Iterable<IClass> descendOnce() {
    return new ValueReferencePairIterable();
  }

  /**
   * Accept the {@code visitor} to visit this instance for double dispatch.
   **/
  @Override
  public void accept(IVisitor visitor) {
    visitor.visitValueReferencePair(this);
  }

  /**
   * Accept the {@code visitor} to visit this instance for double dispatch
   * with the {@code context}.
   **/
  @Override
  public <ContextT> void accept(
      IVisitorWithContext<ContextT> visitor,
      ContextT context) {
    visitor.visitValueReferencePair(this, context);
  }

  /**
   * Accept the {@code transformer} to visit this instance for double dispatch.
   **/
  @Override
  public <T> T transform(ITransformer<T> transformer) {
    return transformer.transformValueReferencePair(this);
  }

  /**
   * Accept the {@code transformer} to visit this instance for double dispatch
   * with the {@code context}.
   **/
  @Override
  public <ContextT, T> T transform(
      ITransformerWithContext<ContextT, T> transformer,
      ContextT context) {
    return transformer.transformValueReferencePair(this, context);
  }

  private class ValueReferencePairIterable implements Iterable<IClass> {
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

      if (valueId != null) {
        memberStream = Stream.concat(memberStream,
          Stream.<IClass>of(ValueReferencePair.this.valueId));
      }

      return memberStream;
    }
  }

  private class ValueReferencePairRecursiveIterable implements Iterable<IClass> {
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

      if (valueId != null) {
        memberStream = Stream.concat(memberStream,
          Stream.concat(Stream.<IClass>of(ValueReferencePair.this.valueId),
            StreamSupport.stream(ValueReferencePair.this.valueId.descend().spliterator(), false)));
      }

      return memberStream;
    }
  }
}

/*
 * This code has been automatically generated by aas-core-codegen.
 * Do NOT edit or append.
 */
