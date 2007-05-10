/*
 * Copyright (C) 2007 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.common.collect;

import com.google.common.base.Objects;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;

/**
 * Implementation of {@code Multimap} using hash tables.
 *
 * <p>The multimap does not store duplicate key-value pairs. Adding a new
 * key-value pair equal to an existing key-value pair has no effect.
 *
 * <p>Keys and values may be null.
 *
 * <p>These methods runs in constant time: {@code asMap}, {@code containsEntry},
 * {@code containsKey}, {@code entries}, {@code get}, {@code isEmpty}, {@code
 * keySet}, {@code put}, {@code remove}, {@code removeAll}, {@code size}, and
 * {@code values}. The processing time of the {@code putAll} and {@code
 * removeAll} methods is proportional to the number of added values.  The {@code
 * clear}, {@code containsValue}, and {@code keys} runtime is proportional to
 * the number of distinct keys. The {@code equals}, {@code hashCode}, {@code
 * toString} and {@code clone} processing time scales with the total number of
 * values in the multimap.
 *
 * <p>This class is not threadsafe when any concurrent operations update the
 * multimap. Concurrent read operations will work correctly. To allow concurrent
 * update operations, wrap your multimap with a call to {@link
 * Multimaps#synchronizedSetMultimap}.
 *
 * @author jlevy@google.com (Jared Levy)
 */
public final class HashMultimap<K,V> extends AbstractSetMultimap<K,V>
    implements Cloneable {
  private static final long serialVersionUID = 4309375142408689415L;

  /** Constructs an empty {@code HashMultimap}. */
  public HashMultimap() {
    super(new HashMap<K, Collection<V>>());
  }

  /**
   * Constructs a {@code HashMultimap} with the same mappings as the specified
   * {@code Multimap}. If a key-value mapping appears multiple times in the
   * input multimap, it only appears once in the constructed multimap.
   *
   * @param multimap the multimap whose contents are copied to this multimap.
   * @see #putAll(Multimap)
   */
  public HashMultimap(Multimap<? extends K, ? extends V> multimap) {
    this();
    putAll(Objects.nonNull(multimap));
  }

  /**
   * Creates an empty {@code HashSet} for a collection of values for one key.
   *
   * @return A new {@code HashSet} containing a collection of values for one
   *     key
   */
  @Override protected Set<V> createCollection() {
    return new HashSet<V>();
  }

  @Override public HashMultimap<K,V> clone() {
    return new HashMultimap<K,V>(this); // okay because we're final
  }

  /*
   * The following method simply calls the superclass methods and is included
   * here for documentation purposes only.
   */

  /**
   * {@inheritDoc}
   *
   * <p>The iterator generated by the returned set traverses the values for one
   * key, followed by the values of a second key, and so on.
   */
  @Override public Set<Entry<K,V>> entries() {
    return super.entries();
  }
}
