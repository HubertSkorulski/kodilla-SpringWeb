package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TrelloMapperTestSuite {

    @Autowired
    private TrelloMapper trelloMapper;

    @Test
    void mapToBoardsTest() {
        //Given
        List<TrelloListDto> trelloListDtos = new ArrayList<>();
        List<TrelloBoardDto> trelloBoardsDto = new ArrayList<>();

        TrelloListDto trelloListDto = new TrelloListDto("1","First list", true);
        TrelloListDto secondTrelloListDto = new TrelloListDto("2", "Second list", false);

        trelloListDtos.add(trelloListDto);
        trelloListDtos.add(secondTrelloListDto);

        TrelloBoardDto trelloBoardDto = new TrelloBoardDto("1","Test name", trelloListDtos);
        TrelloBoardDto secondTrelloBoardDto = new TrelloBoardDto("2", "Second name", trelloListDtos);

        trelloBoardsDto.add(trelloBoardDto);
        trelloBoardsDto.add(secondTrelloBoardDto);

        //When
        List<TrelloBoard> trelloBoards = trelloMapper.mapToBoards(trelloBoardsDto);
        TrelloBoard firstTrelloBoard = trelloBoards.get(0);
        TrelloBoard secondTrelloBoard = trelloBoards.get(1);
        TrelloBoard expectedFirstTrelloBoard = new TrelloBoard("1", "Test name", trelloMapper.mapToList(trelloListDtos));
        TrelloBoard expectedSecondTrelloBoard = new TrelloBoard("2","Second name", trelloMapper.mapToList(trelloListDtos));

        //Then
        assertEquals(expectedFirstTrelloBoard.getId(),firstTrelloBoard.getId());
        assertEquals(expectedFirstTrelloBoard.getName(),firstTrelloBoard.getName());
        assertEquals(expectedFirstTrelloBoard.getLists().get(1).getName(),firstTrelloBoard.getLists().get(1).getName());
        assertEquals(expectedSecondTrelloBoard.getId(), secondTrelloBoard.getId());
        assertEquals(expectedSecondTrelloBoard.getName(), secondTrelloBoard.getName());
        assertEquals(expectedSecondTrelloBoard.getLists().get(1).getName(),secondTrelloBoard.getLists().get(1).getName());
    }

    @Test
    void mapToBoardsDtoTest() {
        //Given
        List<TrelloList> trelloLists = new ArrayList<>();
        List<TrelloBoard> trelloBoards = new ArrayList<>();

        TrelloList trelloList = new TrelloList("1","First list", true);
        TrelloList secondTrelloList = new TrelloList("2", "Second list", false);

        trelloLists.add(trelloList);
        trelloLists.add(secondTrelloList);

        TrelloBoard trelloBoard = new TrelloBoard("1","Trello board", trelloLists);
        TrelloBoard secondTrelloBoard = new TrelloBoard("2","Second Trello board", trelloLists);

        trelloBoards.add(trelloBoard);
        trelloBoards.add(secondTrelloBoard);
        //When
        List<TrelloBoardDto> trelloBoardsDto = trelloMapper.mapToBoardsDto(trelloBoards);
        TrelloBoardDto firstTrelloBoardDto = trelloBoardsDto.get(0);
        TrelloBoardDto secondTrelloBoardDto = trelloBoardsDto.get(1);
        TrelloBoardDto expectedFirstTrelloBoardDto = new TrelloBoardDto("1","Trello board",trelloMapper.mapToListDto(trelloLists));
        TrelloBoardDto expectedSecondTrelloBoardDto = new TrelloBoardDto("2","Second Trello board",trelloMapper.mapToListDto(trelloLists));
        //Then
        assertEquals(expectedFirstTrelloBoardDto.getId(),firstTrelloBoardDto.getId());
        assertEquals(expectedFirstTrelloBoardDto.getName(),firstTrelloBoardDto.getName());
        assertEquals(expectedFirstTrelloBoardDto.getLists().get(1).getName(),firstTrelloBoardDto.getLists().get(1).getName());
        assertEquals(expectedSecondTrelloBoardDto.getId(), secondTrelloBoard.getId());
        assertEquals(expectedSecondTrelloBoardDto.getName(), secondTrelloBoardDto.getName());
        assertEquals(expectedSecondTrelloBoardDto.getLists().get(1).getName(),secondTrelloBoardDto.getLists().get(1).getName());

    }


    @Test
    void mapToListDtoTest() {
        //Given
        TrelloList trelloList = new TrelloList("1","First list",true);
        TrelloList secondTrelloList = new TrelloList("2", "Second list", false);
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(trelloList);
        trelloLists.add(secondTrelloList);
        //When
        List<TrelloListDto> trelloListDtos = trelloMapper.mapToListDto(trelloLists);
        TrelloListDto expectedFirstTrelloListDto = new TrelloListDto("1","First list", true);
        TrelloListDto expectedSecondTrelloListDto = new TrelloListDto("2", "Second list", false);
        TrelloListDto firstTrelloListDto = trelloListDtos.get(0);
        TrelloListDto secondTrelloListDto = trelloListDtos.get(1);
        //Then
        assertEquals(expectedFirstTrelloListDto.getId(),firstTrelloListDto.getId());
        assertEquals(expectedFirstTrelloListDto.getName(),firstTrelloListDto.getName());
        assertEquals(expectedFirstTrelloListDto.isClosed(),firstTrelloListDto.isClosed());
        assertEquals(expectedSecondTrelloListDto.getId(),secondTrelloListDto.getId());
        assertEquals(expectedSecondTrelloListDto.getName(),secondTrelloListDto.getName());
        assertEquals(expectedSecondTrelloListDto.isClosed(),secondTrelloListDto.isClosed());
    }

    @Test
    void mapToListTest() {
        //Given
        TrelloListDto trelloListDto = new TrelloListDto("1","First list", true);
        TrelloListDto secondTrelloListDto = new TrelloListDto("2", "Second list", false);
        List<TrelloListDto> trelloListDtos = new ArrayList<>();
        trelloListDtos.add(trelloListDto);
        trelloListDtos.add(secondTrelloListDto);
        //When
        List<TrelloList> trelloLists = trelloMapper.mapToList(trelloListDtos);
        TrelloList expectedFirstTrelloList = new TrelloList("1","First list", true);
        TrelloList expectedSecondTrelloList = new TrelloList("2", "Second list", false);
        TrelloList firstTrelloList = trelloLists.get(0);
        TrelloList secondTrelloList = trelloLists.get(1);
        //Then
        assertEquals(expectedFirstTrelloList.getId(),firstTrelloList.getId());
        assertEquals(expectedFirstTrelloList.getName(),firstTrelloList.getName());
        assertEquals(expectedFirstTrelloList.isClosed(),firstTrelloList.isClosed());
        assertEquals(expectedSecondTrelloList.getId(),secondTrelloList.getId());
        assertEquals(expectedSecondTrelloList.getName(),secondTrelloList.getName());
        assertEquals(expectedSecondTrelloList.isClosed(),secondTrelloList.isClosed());

    }

    @Test
    void mapToCardDtoTest() {
        //Given
        TrelloCard trelloCard = new TrelloCard("First card", "First card description", "1","1");
        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);
        TrelloCardDto expectedCardDto = new TrelloCardDto("First card", "First card description", "1", "1");
        //Then
        assertEquals(expectedCardDto.getName(),trelloCardDto.getName());
        assertEquals(expectedCardDto.getDescription(),trelloCardDto.getDescription());
        assertEquals(expectedCardDto.getPos(),trelloCardDto.getPos());
        assertEquals(expectedCardDto.getListId(),trelloCardDto.getListId());
    }

    @Test
    void mapToCardTest() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("Second card", "Second card description", "2", "2");
        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);
        TrelloCard expectedCard = new TrelloCard("Second card", "Second card description", "2", "2");
        //Then
        assertEquals(expectedCard.getName(),trelloCard.getName());
        assertEquals(expectedCard.getDescription(),trelloCard.getDescription());
        assertEquals(expectedCard.getPos(),trelloCard.getPos());
        assertEquals(expectedCard.getListId(),trelloCard.getListId());
    }
}
