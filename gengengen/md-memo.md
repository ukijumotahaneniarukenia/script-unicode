```
    private static Set<Integer> genUnicodeCodePoint(Integer s,Integer e){
        Set<Integer> codePointList = new LinkedHashSet<>();
        Set<Integer> lowerCodePointList = IntStream.rangeClosed(s,e).boxed().filter(codePoint -> codePoint < (int) Character.MIN_HIGH_SURROGATE).collect(Collectors.toSet());
        Set<Integer> upperCodePointList = IntStream.rangeClosed(s,e).boxed().filter(codePoint -> codePoint > (int) Character.MAX_HIGH_SURROGATE).collect(Collectors.toSet());
        Set<Integer> poperCodePointList = IntStream.rangeClosed(s,e).boxed().filter(codePoint -> codePoint < 160).collect(Collectors.toSet());
        codePointList.addAll(lowerCodePointList);
        codePointList.addAll(upperCodePointList);
        codePointList.addAll(poperCodePointList);
        return codePointList;
    }
```
