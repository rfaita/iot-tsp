package com.iot.tsp.command;

import java.util.*;

class Solution {

    private static final List<List<List<String>>> THREE_MEMBERS_FAMILY_FREE_PATTERNS;

    static {
        THREE_MEMBERS_FAMILY_FREE_PATTERNS = new ArrayList<>();

        THREE_MEMBERS_FAMILY_FREE_PATTERNS.add(Arrays.asList(Arrays.asList("A", "B", "C")));
        THREE_MEMBERS_FAMILY_FREE_PATTERNS.add(Arrays.asList(Arrays.asList("D", "E", "F"), Arrays.asList("E", "F", "G")));
        THREE_MEMBERS_FAMILY_FREE_PATTERNS.add(Arrays.asList(Arrays.asList("H", "J", "K")));
    }

    public static void main(String[] args) {

        Solution s = new Solution();

        System.out.println(s.solution(2, "1A 2F 1C"));//4
        System.out.println(s.solution(2, "1E"));//3
        System.out.println(s.solution(4, "1A 2F 1C 4A 3D 4C 3C 2G 4K"));//7
        System.out.println(s.solution(5, "1A 2F 1C 4A 3D 4C 3C 2G 4K"));//10
        System.out.println(s.solution(40, "1A 3C 2B 40G 5A"));//116
        System.out.println(s.solution(40, null));//120
        System.out.println(s.solution(0, "1A"));//0
        System.out.println(s.solution(51, "37A 37B 37C 37D 37F 37H 38A 38B 38C 38D 38F 38H 39A 39B 39C 39D 39F 39H 40A 40B 40C 40D 40F 40H 34A 34B 34C 34D 34F 34H 35A 35B 35C 35D 35F 35H 36A 36B 36C 36D 36F 36H 41A 41B 41C 41D 41F 41H 42A 42B 42C 42D 42F 42H 43A 43B 43C 43D 43F 43H 44A 44B 44C 44D 44F 44H 45A 45B 45C 45D 45F 45H 46A 46B 46C 46D 46F 46H 47A 47B 47C 47D 47F 47H 48A 48B 48C 48D 48F 48H 49A 49B 49C 49D 49F 49H 50A 50B 50C 50D 50F 50H 1A 1B 1C 1D 1F 1H 2A 2B 2C 29F 29H 30A 30B 30C 30D 30F 30H 31A 31B 31C 31D 31F 31H 32A 32B 32C 32D 32F 32H 33A 2D 2F 2H 3A 3B 3C 3D 3F 3H 4A 4B 4C 4D 4F 4H 5A 5B 5C 5D 5F 5H 6A 6B 6C 6D 6F 6H 7A 7B 7C 7D 7F 7H 8A 8B 8C 8D 8F 8H 9A 9B 9C 9D 9F 9H 10A 10B 10C 10D 10F 10H 11A 11B 11C 11D 11F 11H 12A 12B 12C 12D 12F 12H 13A 13B 13C 13D 13F 13H 14A 14B 14C 14D 14F 14H 15A 15B 15C 15D 15F 15H 16A 16B 16C 16D 16F 16H 17A 17B 17C 17D 17F 17H 18A 18B 18C 18D 18F 18H 19A 19B 19C 19D 19F 19H 20A 20B 20C 20D 20F 20H 21A 21B 21C 21D 21F 21H 22A 22B 22C 22D 22F 22H 23A 23B 23C 23D 23F 23H 24A 24B 24C 24D 24F 24H 25A 25B 25C 25D 25F 25H 26A 26B 26C 26D 26F 26H 27A 27B 27C 27D 27F 27H 28A 28B 28C 28D 28F 28H 29A 29B 29C 29D 33B 33C 33D 33F 33H"));//150


    }

    public int solution(int N, String S) {

        int solutions = 0;

        Map<Integer, List<String>> occupiedSeats = parseOccupiedSeats(S);


        for (Integer currentRow = 1; currentRow <= N; currentRow++) {

            List<String> occupiedColumnsByRow = occupiedSeats.getOrDefault(currentRow, new ArrayList<>());

            for (List<List<String>> patternRow : THREE_MEMBERS_FAMILY_FREE_PATTERNS) {

                if (verifyPatternRow(patternRow, occupiedColumnsByRow)) {
                    solutions++;
                }

            }
        }

        return solutions;
    }


    private Boolean verifyPatternRow(List<List<String>> patternRow, List<String> occupiedColumnsByRow) {
        if (occupiedColumnsByRow.isEmpty()) {
            return Boolean.TRUE;
        }
        for (List<String> pattern : patternRow) {

            Boolean patternApproved = Boolean.TRUE;
            for (String occupiedColumn : occupiedColumnsByRow) {
                if (pattern.contains(occupiedColumn)) {
                    patternApproved = Boolean.FALSE;
                }
            }
            if (patternApproved) {
                return Boolean.TRUE;
            }
        }

        return Boolean.FALSE;
    }

    private Map<Integer, List<String>> parseOccupiedSeats(String S) {

        Map<Integer, List<String>> ret = new HashMap<>();

        if (S != null && !S.isEmpty()) {
            String[] data = S.split(" ");

            for (String occupiedSeat : data) {
                Integer row = getRow(occupiedSeat);

                List<String> occupiedColumns = ret.getOrDefault(row, new ArrayList<>());

                occupiedColumns.add(getColumn(occupiedSeat));

                ret.put(row, occupiedColumns);

            }
        }
        return ret;
    }

    private Integer getRow(String occupiedSeat) {
        return Integer.valueOf(occupiedSeat.substring(0, occupiedSeat.length() - 1));
    }

    private String getColumn(String occupiedSeat) {
        return (occupiedSeat.substring(occupiedSeat.length() - 1));
    }


}