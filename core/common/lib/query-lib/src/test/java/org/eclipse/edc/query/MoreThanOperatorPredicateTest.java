/*
 *  Copyright (c) 2025 Gradiant
 *
 *  This program and the accompanying materials are made available under the
 *  terms of the Apache License, Version 2.0 which is available at
 *  https://www.apache.org/licenses/LICENSE-2.0
 *
 *  SPDX-License-Identifier: Apache-2.0
 *
 *  Contributors:
 *       Gradiant - initial API and implementation
 *
 */

package org.eclipse.edc.query;

import org.eclipse.edc.spi.query.OperatorPredicate;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class MoreThanOperatorPredicateTest {

    private final OperatorPredicate predicate = new MoreThanOperatorPredicate();

    @ParameterizedTest
    @ArgumentsSource(ValidValues.class)
    void shouldReturnTrue_whenValueMoreThanComparedOne(Object value, Object comparedTo) {
        assertThat(predicate.test(value, comparedTo)).isTrue();
    }

    @ParameterizedTest
    @ArgumentsSource(InvalidValues.class)
    void shouldReturnFalse_whenValueNotMoreThanComparedOne(Object value, Object comparedTo) {
        assertThat(predicate.test(value, comparedTo)).isFalse();
    }

    private static class ValidValues implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    arguments(2, 1),
                    arguments(2, 1L),
                    arguments(2, 1.99f),
                    arguments(2, 1.99d),
                    arguments("b", "a")
            );
        }
    }

    private static class InvalidValues implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    arguments(1, 1),
                    arguments(1, 1L),
                    arguments(1, 1.0f),
                    arguments(1, 1.0d),
                    arguments("a", "a")
            );
        }
    }

}
