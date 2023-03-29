package br.com.victor.restwithspringbootandjava.mapper;

import java.util.ArrayList;
import java.util.List;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

public class DozzerMapper {

    private static Mapper mapper = DozerBeanMapperBuilder.buildDefault();

    public static <O, D> D parseObject(O origin, Class<D> destination) {
        return mapper.map(origin, destination);

    }

    public static <O, D> List<D> parseListObjects(List<O> origin, Class<D> destination) {
        List<D> destinationsObjects = new ArrayList<D>();
        for (O o : origin) {
            destinationsObjects.add(mapper.map(o, destination));
        }
        return destinationsObjects;

    }
}
