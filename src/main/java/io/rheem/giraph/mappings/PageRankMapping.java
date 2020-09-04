package io.rheem.giraph.mappings;

import io.rheem.basic.operators.PageRankOperator;
import io.rheem.core.mapping.*;
import io.rheem.core.plan.rheemplan.Operator;
import io.rheem.core.plan.rheemplan.Subplan;
import io.rheem.giraph.operators.GiraphPageRankOperator;
import io.rheem.giraph.platform.GiraphPlatform;

import java.util.Collection;
import java.util.Collections;

/**
 * Maps {@link PageRankOperator}s to {@link GiraphPageRankOperator}s.
 */
public class PageRankMapping implements Mapping {

    @Override
    public Collection<PlanTransformation> getTransformations() {
        return Collections.singleton(
                new PlanTransformation(
                        this.createSubplanPattern(),
                        this.createReplacementSubplanFactory(),
                        GiraphPlatform.getInstance()
                )
        );
    }

    @SuppressWarnings("unchecked")
    private SubplanPattern createSubplanPattern() {
        final OperatorPattern operatorPattern = new OperatorPattern(
                "pageRank", new PageRankOperator(1), false);
        return SubplanPattern.createSingleton(operatorPattern);
    }

    private ReplacementSubplanFactory createReplacementSubplanFactory() {
        return new ReplacementSubplanFactory.OfSingleOperators<PageRankOperator>(
                (matchedOperator, epoch) -> new GiraphPageRankOperator(matchedOperator).at(epoch)
        );
    }
}
